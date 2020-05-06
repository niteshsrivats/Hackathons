import React, {Component, Fragment} from "react";
import {Col, Layout, Row, Typography} from "antd";
import Logger from "../components/sider/Logger";
import axios from "axios";
import PageHeader from "../components/layout/PageHeader";
import WorkflowCard from "../components/cards/WorkflowCard";

const {Title} = Typography;

const {Footer, Content} = Layout;

class Dashboard extends Component {
    state = {
        workflows: null
    };

    componentDidMount = async () => {
        const workflows = await axios.get(`http://localhost:8080/workflows`)
            .then((response) => response.data)
            .catch((error) => console.log(error));
        this.setState({workflows: workflows});
    };

    render() {
        if (this.state.workflows) {
            return (
                <Layout>
                    <PageHeader button="Create Workflows" search="Search Workflow"/>
                    <Content style={{margin: '0 16px'}}>
                        <Row type="flex" justify="space-between">
                            <Col span={18}>
                                <Title level={2}>Workflows</Title>
                                {this.state.workflows.map((workflow) => {
                                        return (
                                            <WorkflowCard data={workflow}/>
                                        )
                                    }
                                )}
                            </Col>
                            <Col span={6} style={{
                                background: 'white',
                                height: '100vh',
                                borderLeft: '1px solid rgba(0,0,0,0.1)'
                            }}>
                                <Logger/>
                            </Col>
                        </Row>
                    </Content>
                    <Footer style={{textAlign: 'center'}}>Created by The Boys</Footer>
                </Layout>
            )
        } else {
            return <Fragment/>
        }
    }
}


export default Dashboard;
