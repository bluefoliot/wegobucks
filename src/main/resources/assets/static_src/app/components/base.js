import React from 'react';
import { Link } from 'react-router';

const Base = React.createClass({
    render: function() {
        return (
            <div>
                <div className="jumbotron text-center">
                    <Link to="/" className="logo"><h1>Wegobucks</h1></Link>
                </div>
                {this.props.navTab}
                <div className="container">
                    {this.props.content}
                </div>
            </div>
        );
    }
});

export default Base;
