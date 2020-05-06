import React from 'react';
import {Layout, Menu} from 'antd';
import {
  DownOutlined,
  LineChartOutlined,
  MenuFoldOutlined,
  MenuUnfoldOutlined,
  ProjectOutlined,
  QuestionCircleOutlined,
  SearchOutlined
} from '@ant-design/icons';
import {BrowserRouter as Router, Link, Route, Switch} from 'react-router-dom';

import 'antd/dist/antd.css';
import './custom.css';

import DashboardScreen from './components/DashboardScreen';
import ProjectsScreen from './components/ProjectsScreen';
import ProjectScreen from './components/ProjectScreen';
import SubmissionScreen from './components/SubmissionScreen';
import LoginScreen from './components/LoginScreen';

const {Header, Sider, Content} = Layout;

class App extends React.Component {
  state = {
    collapsed: false,
    accessToken: '',
  };

  async componentDidMount() {
    try {
      const accessToken = await localStorage.getItem('accessToken');
      this.setState({accessToken});
    } catch (error) {
      console.log(error);
    }
  }

  toggle = () => {
    this.setState({
      collapsed: !this.state.collapsed
    });
  };

  // PrivateRoute = async ({ component: Component, ...rest }) => {
  //   const accessToken = await localStorage.getItem('accessToken');
  //   return (
  // <Route {...rest} render={(props) => (
  //   accessToken
  //     ? <Component {...props} />
  //     : <Redirect to='/login' />
  // )} />
  // )}
  render() {
    return (
        <Router>
          <Layout style={{minHeight: '100vh'}}>
            {this.state.accessToken && <Sider trigger={null} collapsible collapsed={this.state.collapsed}>
              <div style={{backgroundColor: 'white'}}>
                <div className="logo"/>
              </div>
              <Menu
                  mode="inline"
                  defaultSelectedKeys={['1']}
                  style={{height: '100%'}}
              >
                <Menu.Item key="1" style={{marginTop: 32}}>
                  <Link to="/">
                    <LineChartOutlined/>
                    <span>Dashboard</span>
                  </Link>
                </Menu.Item>
                <Menu.Item key="2">
                  <Link to="/projects">
                    <ProjectOutlined/>
                    <span>Projects</span>
                  </Link>
                </Menu.Item>
              </Menu>
            </Sider>}

            <Layout className="site-layout">
              {this.state.accessToken && <Header
                  className="site-layout-background"
                  style={{padding: 0, display: 'flex'}}
              >
                {React.createElement(
                    this.state.collapsed ? MenuUnfoldOutlined : MenuFoldOutlined,
                    {
                      className: 'trigger',
                      onClick: this.toggle
                    }
                )}
                <Menu
                    mode="horizontal"
                    style={{lineHeight: '64px', marginLeft: 'auto'}}
                >
                  <Menu.Item key="1">
                    <SearchOutlined/>
                  </Menu.Item>
                  <Menu.Item key="2">
                    <QuestionCircleOutlined/>
                  </Menu.Item>
                  <Menu.Item key="3">
                    Rohan Mayya <DownOutlined/>
                  </Menu.Item>
                  <Menu.Item key="4" onClick={this.handleLogout}>
                    Logout
                  </Menu.Item>
                </Menu>
              </Header>}
              <Content
                  style={{
                    margin: '24px 16px',
                    padding: 24,
                    minHeight: 280,
                    backgroundColor: 'rgba(255,255,255,0.8)'
                  }}
              >
                <Switch>
                  <Route exact path="/login" component={LoginScreen}/>
                  {/* <this.PrivateRoute exact path='/' component={DashboardScreen}/>
                <this.PrivateRoute exact path='/projects' component={ProjectScreen} />
                <this.PrivateRoute exact path='/project' component={ProjectScreen} />
                <this.PrivateRoute exact path='/submission' component={SubmissionScreen} /> */}
                  <Route exact path="/" component={DashboardScreen}/>
                  <Route exact path="/projects" component={ProjectsScreen}/>
                  <Route exact path="/projects/:id" component={ProjectScreen}/>
                  <Route
                      exact
                      path="/submissions/:id"
                      component={SubmissionScreen}
                  />
                </Switch>
              </Content>
            </Layout>
          </Layout>
        </Router>
    );
  }

  handleLogout = () => {
    localStorage.clear();
  };
}


export default App;
