import React, { Component, Fragment } from "react";
import { Button, Col, Collapse, Layout, Progress, Row, Steps, Typography } from "antd";
import axios from "axios";
import PageHeader from "../components/layout/PageHeader";

const { Panel } = Collapse;
const { Title, Text } = Typography;
const { Step } = Steps;
const { Content, Footer } = Layout;

class StoryPage extends Component {

    state = {
        process: null,
        inProcess: 0
    };

    componentDidMount = async () => {
        const process = await axios.get(`http://localhost:8080/processes/${this.props.match.params.processId}`)
            .then((response) => response.data)
            .catch((error) => console.log(error));
        this.setState({ process: process });
    };

    Timeline = () => {
        if (this.state.process) {
            const { stories } = this.state.process;
            let progress = false;
            return (
                <Fragment>
                    <Title level={4}>Timeline</Title>
                    <Steps current={3} style={{ padding: 20 }}>
                        {
                            stories.map((story, index) => {
                                if (story.status) {
                                    return <Step status={"finish"} title={story.name} />
                                } else if (progress) {
                                    return <Step status={"waiting"} title={story.name} />
                                } else {
                                    progress = true;
                                    return <Step status={"process"} title={story.name} />
                                }
                            })
                        }
                    </Steps>
                </Fragment>
            );
        } else {
            return <Fragment />
        }
    };

    getPercentage = (tasks) => {
        let count = 0;
        for (let i = 0; i < tasks.length; i++) {
            if (tasks[i].status) {
                count++;
            }
        }
        return Math.floor((count * 100) / tasks.length);
    };

    ProcessBreakdown = () => {
        if (this.state.process) {
            const { stories } = this.state.process;
            return (
                <>
                    <Title level={4}>Process Breakdown</Title>
                    <div style={{
                        backgroundColor: '#fff', padding: '20px'
                    }}>
                        {
                            stories.map((story) => {
                                if (story.status) {
                                    return (
                                        <Fragment>
                                            <Button type="link"
                                                href={`http://localhost:3000/story/${story.id}`}>{story.name}</Button>
                                            <Progress percent={100} size="small" />
                                        </Fragment>
                                    );
                                } else {
                                    return (
                                        <Fragment>
                                            <Button type="link"
                                                href={`http://localhost:3000/story/${story.id}`}>{story.name}</Button>
                                            <Progress percent={this.getPercentage(story.tasks)} size="small" />
                                        </Fragment>
                                    );
                                }
                            })
                        }
                    </div>
                </>
            );
        } else {
            return <Fragment />
        }
    };

    Folders = () => {
        if (this.state.process) {
            const { stories } = this.state.process;
            return (
                <Collapse defaultActiveKey={['1']}>
                    {
                        stories.map((story, index) => {
                            return (
                                <Panel header={story.name} key={index}>
                                    {
                                        story.files.map((file) => {
                                            return <Button type="primary" shape="round"
                                                icon="file">{file.name}{file.extension}</Button>
                                        })
                                    }
                                </Panel>
                            );
                        })
                    }
                </Collapse>
            );
        } else {
            return <Fragment />
        }
    };

    render() {
        if (this.state.process) {
            return (
                <Layout>
                    <PageHeader search="Search Story" />
                    <Content style={{ margin: '0 16px', padding: 20 }}>
                        <Title level={2}>{this.state.process.name}</Title>
                        <Text type="secondary">Process ID: #{this.state.process.id}</Text>
                        <br />
                        <br />
                        <Row>
                            <Col>
                                <this.Timeline />
                            </Col>
                        </Row>
                        <br /><br /><br />
                        <Row type="flex" justify="space-between">
                            <Col span={11}>
                                <this.ProcessBreakdown />
                            </Col>
                            <Col span={11}>
                                <Title level={4}>Folders</Title>
                                <this.Folders />
                            </Col>
                        </Row>
                    </Content>
                    <Footer style={{ textAlign: 'center' }}>Created by The Boys</Footer>
                </Layout>
            );
        } else {
            return <Fragment />
        }
    }
}

export default StoryPage;
