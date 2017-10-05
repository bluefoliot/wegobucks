import React from 'react';
import { Router, Route, browserHistory, IndexRoute } from 'react-router';
import Base from './components/base';
import NavTabContainer from './components/containers/nav-tab-container';
import MenuContainer from './components/containers/menu-container';
import OrderFormContainer from './components/containers/order-form-container';
import SalesReportContainer from './components/containers/sales-report-container';


export default (
  <Router history={browserHistory}>
    <Route path='/' component={Base}>
      <IndexRoute components={{navTab: NavTabContainer, content: MenuContainer}}/>
      <Route path='order' components={{navTab: NavTabContainer, content: OrderFormContainer}} />
      <Route path='sales' components={{navTab: NavTabContainer, content: SalesReportContainer}} />
    </Route>
  </Router>
);
