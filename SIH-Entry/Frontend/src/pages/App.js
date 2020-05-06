import React, {Component} from "react";
import {BrowserRouter as Router} from 'react-router-dom';
import {Layout} from "antd";
import Routes from "../routes/routes";
import NavBar from "../components/layout/NavBar";

const {Sider} = Layout;

class App extends Component {
    render() {
        return (
            <Router>
                <Layout style={{minHeight: '100vh'}}>
                    <Sider>
                        <NavBar/>
                    </Sider>
                    <Routes/>
                </Layout>
            </Router>
        );
    }
}

export default App;
