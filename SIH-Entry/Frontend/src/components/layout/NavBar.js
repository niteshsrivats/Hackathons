import {Link} from "react-router-dom";
import React from "react";
import {Menu} from 'antd';

class NavBar extends React.Component {
    render() {
        return (
            <Menu theme="dark"
                  onClick={this.handleClick}
                  mode="inline"
            >
                <Menu.Item key="dashboard" style={{float: 'left'}}>
                    <Link to="/">Dashboard</Link>
                </Menu.Item>
                <Menu.Item key="drive" style={{float: 'left'}}>
                    <Link to="/drive">Drive</Link>
                </Menu.Item>
                <Menu.Item key="users" style={{float: 'left'}}>
                    <Link to="/users">Groups</Link>
                </Menu.Item>
                <Menu.Item key="inbox" style={{float: 'right'}}>
                    <Link to="/inbox">Inbox</Link>
                </Menu.Item>
            </Menu>
        );
    }
}

export default NavBar;
