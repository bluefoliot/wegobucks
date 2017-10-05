'use strict';

module.exports = function (grunt) {
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),

        env: {
            dev: {
                NODE_ENV: 'development'
            },

            prod: {
                NODE_ENV: 'production'
            }
        },

        browserify: {
            options: {
              transform: [
                ["babelify", {"presets": ["env", "react"], "plugins": ["jsx-control-statements"]}]
              ]
            },

            dev:{
                options:{
                    watch: true,
                    livereload: true
                },
                files: {
                  "../static/js/bundle.js": ["app/*.js"]
                }
            },
            prod: {
                files: {
                  "../static/js/bundle.js": ["app/*.js"]
                }
            }


        },

        bower_concat: {
            all:{
                dest:{
                    css: '../static/css/styles.css'
                }
            }
        },

        uglify: {
            options: {
                sourceMap: false,
                mangle: true,
                compress: true
            },
            prod: {
                files: {
                    'js/bundle.js': ['js/bundle.js']
                }
            }
        },
        watch: {
            options: {
                livereload: true
            }
        }
    });

    grunt.loadNpmTasks("grunt-browserify");
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-env');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-bower-concat');
    grunt.loadNpmTasks('grunt-contrib-connect');

    grunt.registerTask('default', ['env:dev', 'bower_concat', 'browserify:dev', 'watch']);
    grunt.registerTask('prod', ['env:prod', 'bower_concat', 'browserify:prod', 'uglify:prod']);

};
