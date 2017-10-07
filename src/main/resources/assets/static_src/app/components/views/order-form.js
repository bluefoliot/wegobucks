import React from 'react';

class OrderForm extends React.Component{
  render() {
    return (
      <div className='row'>
        <div className='col-md-8 col-md-offset-2 col-xs-10 col-xs-offset-1'>
          <div className="panel panel-default">
            <div className="panel-heading">
              Place your order
            </div>
            <div className="panel-body">
              <div className='row'>
                <div className='col-md-6 col-xs-12'>
                  <div className="form-group">
                    <label>Select your drink</label>
                    <select className="form-control" onChange={this.props.setDrink}>
                      <option key={-1} value={''}>
                        - select -
                      </option>
                      <For each="drink" index="idx" of={this.props.drinks}>
                        <option value={drink.slug}>
                          {drink.name}
                        </option>
                      </For>
                    </select>
                  </div>
                  <If condition={this.props.drink}>
                    <div className="form-group">
                      <label>Select size</label>
                      <select className="form-control" value={this.props.sizeSlug} onChange={this.props.setSize}>
                        <option key={-1} value={''}>
                          - select -
                        </option>
                        <For each="price" index="idx" of={this.props.drink.priceList}>
                          <If condition={price.price}>
                            <option key={idx} value={price.sizeSlug}>
                              {price.size}
                            </option>
                          </If>
                        </For>
                      </select>
                    </div>
                  </If>
                  <If condition={this.props.price}>
                    <div className="form-group">
                      <span>Drink price: {this.props.price}</span>
                    </div>
                    <div className="form-group">
                      <button className="btn btn-default" onClick={this.props.placeOrder}>Order</button>
                    </div>
                  </If>
                  <div className="form-group">
                    <Choose>
                      <When condition={this.props.orderStatus=='success' && this.props.order}>
                        <span>Order successful!</span>
                        <br/>
                        <span>Order id: {this.props.order.id}</span>
                        <br/>
                        <span>Name: {this.props.order.name}</span>
                        <br/>
                        <span>Size: {this.props.order.size}</span>
                        <br/>
                        <span>Amount: {this.props.order.amount}</span>
                      </When>
                      <When condition={this.props.orderStatus=='fail'}>
                        <span>Order failed, please try again.</span>
                      </When>
                    </Choose>
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

export default OrderForm;
