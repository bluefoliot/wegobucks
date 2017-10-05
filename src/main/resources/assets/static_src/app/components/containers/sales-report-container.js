import React from 'react';
import {connect} from 'react-redux';
import * as drinkApi from '../../api/drink-api';
import * as orderApi from '../../api/order-api';
import SalesReport from '../views/sales-report';

const SalesReportContainer = React.createClass({
  getInitialState: function() {
    return {
      groupBy: ''
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

  render: function() {
    return (
      <SalesReport
        {...this.props}
        {...this.state}
        updateGroupBy={this.updateGroupBy}
        filterBySize={this.filterBySize}
        filterByType={this.filterByType} />
    );
  }
});

const mapStateToProps = store => {
    return {
        sizes: store.menuState.sizes,
        types: store.menuState.drinkTypes,
        sales: store.salesState.sales
    };
}

export default connect(mapStateToProps)(SalesReportContainer);
