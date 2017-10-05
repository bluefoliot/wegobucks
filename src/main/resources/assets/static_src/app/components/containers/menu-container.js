import React from 'react';
import {connect} from 'react-redux';
import Menu from '../views/menu';
import * as drinkApi from '../../api/drink-api';

const MenuContainer = React.createClass({

  componentDidMount: function(){
    this.props.dispatch(drinkApi.loadAllSizes());
    this.props.dispatch(drinkApi.loadAllDrinks()); 
  },

  render: function(){
        return (
            <Menu {...this.props} />
        );
    }
});

const mapStateToProps = store => {
    return {
        drinks: store.menuState.drinks,
        sizes: store.menuState.sizes
    };
}

export default connect(mapStateToProps)(MenuContainer);
