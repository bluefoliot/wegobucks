import React from 'react';


class LoginForm extends React.Component{

    render(){
        return (
          <div className='row'>
            <div className='col-md-8 col-md-offset-2 col-xs-10 col-xs-offset-1'>
              <div className="panel panel-default">
                <div className="panel-heading">
                  Login form
                </div>
                <div className="panel-body">
                  <div className='row'>
                    <div className='col-md-6 col-xs-12'>
                      <div className="form-group">
                        <label>Please enter your name before continuing</label>
                        <input
                          type="text"
                          className="form-control"
                          value={this.props.name}
                          onChange={this.props.updateName}/>
                      </div>
                      <div className="form-group">
                        <button className="btn btn-default" onClick={this.props.login}>Login</button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        );
    }
}

export default LoginForm;
