import React from 'react';

class Menu extends React.Component{
  render() {
    return (
      <div className='row'>
        <div className='col-md-8 col-md-offset-2 col-xs-10 col-xs-offset-1'>

          <div className="form-group">
            <label>Group sales report by</label>
            <select className="form-control" onChange={this.props.updateGroupBy}>
              <option value="">- select-</option>
              <option value="all">All</option>
              <option value="type">Type</option>
              <option value="size">Size</option>
            </select>
            <Choose>
              <When condition={this.props.groupBy == "size"}>
                <label>Select size</label>
                <select className="form-control" onChange={this.props.filterBySize}>
                  <option value="">- select-</option>
                  <For each="size" index="idx" of={this.props.sizes}>
                    <option value={size.slug} key={idx}>{size.name}</option>
                  </For>
                </select>
              </When>
              <When condition={this.props.groupBy == "type"}>
                <label>Select type</label>
                <select className="form-control" onChange={this.props.filterByType}>
                  <option value="">- select-</option>
                  <For each="type" index="idx" of={this.props.types}>
                    <option value={type.type} key={idx}>{type.type}</option>
                  </For>
                </select>
              </When>
            </Choose>
          </div>

          <div className="panel panel-default">
            <div className="panel-heading">
              Sales report
            </div>
            <div className="panel-body">
              <div className="table-responsive">
                <table className="table table-hover">
                  <thead>
                    <tr>
                      <th>Id</th>
                      <th>Name</th>
                      <th>Size</th>
                      <th>Type</th>
                      <th>Amount</th>
                    </tr>
                  </thead>
                  <tbody>
                    <If condition={this.props.sales.orders}>
                      <For each="item" index="idx" of={this.props.sales.orders}>
                        <tr key={idx}>
                          <td>{item.id}</td>
                          <td>{item.name}</td>
                          <td>{item.size}</td>
                          <td>{item.type}</td>
                          <td>{item.amount}</td>
                        </tr>
                      </For>
                    </If>
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
