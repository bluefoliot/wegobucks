import React from 'react';
import {connect} from 'react-redux';
import * as drinkApi from '../../api/drink-api';
import * as orderApi from '../../api/order-api';
import * as authApi from '../../api/auth-api';
import SalesReport from '../views/sales-report';
import LoginForm from '../views/login-form';

const SalesReportContainer = React.createClass({
  getInitialState: function() {
    return {
      groupBy: '',
      name:''
    };
  },

  componentDidMount: function() {
    this.props.dispatch(orderApi.loadAllSales());
    this.props.dispatch(drinkApi.loadAllSizes());
    this.props.dispatch(drinkApi.loadAllTypes());
  },

  updateGroupBy: function(event) {
    const groupBy = event.target.value;
    this.setState({groupBy: groupBy});
    if(groupBy=="all") {
      this.props.dispatch(orderApi.loadAllSales());
    }
  },

  filterBySize: function(event) {
    const size = event.target.value;
    if(size) {
      this.props.dispatch(orderApi.loadSalesBySize(size));
    }
  },

  filterByType: function(event) {
    const type = event.target.value;
    if(type) {
      this.props.dispatch(orderApi.loadSalesByType(type));
    }
  },

  updateName: function(event) {
    const name = event.target.value;
    this.setState({name: name});
  },

  login: function() {
    if(this.state.name) {
      this.props.dispatch(authApi.login(this.state.name));
    }
  },

  render: function() {
    return (
      <Choose>
        <When condition={this.props.promptLogin}>
          <LoginForm
            {...this.props}
            {...this.state}
            updateName={this.updateName}
            login={this.login} />
        </When>
        <Otherwise>
          <SalesReport
            {...this.props}
            {...this.state}
            updateGroupBy={this.updateGroupBy}
            filterBySize={this.filterBySize}
            filterByType={this.filterByType} />
        </Otherwise>
      </Choose>
    );
  }
});

const mapStateToProps = store => {
    return {
        sizes: store.menuState.sizes,
        types: store.menuState.drinkTypes,
        sales: store.salesState.sales,
        promptLogin: store.authState.promptLogin
    };
}

export default connect(mapStateToProps)(SalesReportContainer);
