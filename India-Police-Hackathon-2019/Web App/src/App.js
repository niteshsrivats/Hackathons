import React, { Component, Fragment } from 'react';
import {
    BrowserRouter as Router,
    Switch,
    Route
    // Link
} from 'react-router-dom';
import Dashboard from './components/Dashboard/Dashboard';
import Officers from './components/Officers/Officers';
import Beats from './components/Beats/Beats';
import Beat from './components/Beats/Beat';
import BeatReport from './components/Beats/BeatReport.js';
import Navigation from './components/Navigation/Navigation';

class App extends Component {
    render() {
        return (
            <Fragment>
                <Router>
                    {/* Navigation bar from antd */}
                    <Navigation />
                    <Switch>
                        <Route exact path="/" component={Dashboard} />
                        <Route path="/officers" component={Officers} />
                        <Route path="/beats" component={Beats} />
                        <Route path="/beat/:beatid" component={Beat} />
                        <Route path="/report/:reportid" component={BeatReport} />
                    </Switch>
                </Router>
            </Fragment>
        );
    }
}
export default App;
