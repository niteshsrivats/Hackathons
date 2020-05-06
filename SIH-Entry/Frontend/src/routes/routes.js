import React, {Component, Fragment} from "react";
import Dashboard from "../pages/Dashboard";
import {Route, Switch} from 'react-router-dom';
import StoryPage from "../pages/StoryPage";
import TaskPage from "../pages/TaskPage";
import ProcessPage from "../pages/ProcessPage";
import Drive from "../pages/Drive";

class Routes extends Component {
    render() {
        return (
            <Fragment>
                <Switch>
                    <Route exact path="/" component={Dashboard}/>
                    <Route exact path="/drive" component={Drive}/>
                    <Route exact path="/workflow/:workflowId" component={ProcessPage}/>
                    <Route exact path="/process/:processId" component={StoryPage}/>
                    <Route exact path="/story/:storyId" component={TaskPage}/>
                </Switch>
            </Fragment>
        )
    }
}

export default Routes;
