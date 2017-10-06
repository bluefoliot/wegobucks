#!/bin/bash

(cd src/main/resources/assets/static_src/;npm install)
(cd src/main/resources/assets/static_src/;bower install)
(cd src/main/resources/assets/static_src/;grunt prod)
java -jar target/wego-*.jar server $1