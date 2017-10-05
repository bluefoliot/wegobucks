import React from 'react';

class Menu extends React.Component{
  render() {
    return (
      <div className='row'>
        <div className='col-md-8 col-md-offset-2 col-xs-10 col-xs-offset-1'>
        <div className="panel panel-default">
          <div className="panel-heading">
              Our Drinks
          </div>
          <div className="panel-body">
              <div className="table-responsive">
                  <table className="table table-hover">
                      <thead>
                          <tr>
                              <th>&nbsp;</th>
                              <For each="size" index="idx" of={this.props.sizes}>
                                <th key={idx}>{size.name}</th>
                              </For>
                          </tr>
                      </thead>
                      <tbody>
                        <For each="drink" index="idx" of={this.props.drinks}>
                          <tr key={idx}>
                            <td>{drink.name}</td>
                            <For each="price" index="priceIdx" of={drink.priceList}>
                              <td key={priceIdx}>
                                <If condition={price.price}>
                                  {price.price}
                                </If>
                              </td>
                            </For>
                          </tr>
                        </For>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
        </div>
      </div>
    );
  }
}

export default Menu;
