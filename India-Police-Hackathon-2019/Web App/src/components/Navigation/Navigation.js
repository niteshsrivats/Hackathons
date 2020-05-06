import React, { Fragment, Component } from 'react';
import { Menu, Icon } from 'antd';
import { Link } from 'react-router-dom';

class Navigation extends Component {
    render() {
        return (
            <Fragment>
                <Menu
                    onClick={this.handleClick}
                    // selectedKeys={[this.state.current]}
                    mode="horizontal"
                >
                    <Menu.Item key="dashboard">
                        <Link to="/">Dashboard</Link>
                    </Menu.Item>
                    <Menu.Item key="officers">
                        <Link to="/officers">Officers</Link>
                    </Menu.Item>

                    <Menu.Item key="beats">
                        <Link to="/beats">Beats</Link>
                    </Menu.Item>
                </Menu>
            </Fragment>
        );
    }
}

export default Navigation;
