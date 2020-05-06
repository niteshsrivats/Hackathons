import React, {Component, Fragment} from "react";
import {Button, Checkbox, Col, Layout, List, Row, Spin, Typography} from "antd";
import axios from "axios";
import PageHeader from "../components/layout/PageHeader";

const {Title, Text} = Typography;
const {Content, Footer} = Layout;

class TaskPage extends Component {
    state = {
        story: null
    };

    componentDidMount = async () => {
        const story = await axios.get(`http://localhost:8080/stories/${this.props.match.params.storyId}`)
            .then((response) => response.data)
            .catch((error) => console.log(error));
        this.setState({story: story});
    };

    render() {
        if (this.state.story) {
            return (<Layout>
                <PageHeader search="Search Task"/>
                <Content style={{margin: '0 16px'}}>
                    <Title level={2}>{this.state.story.name}</Title>
                    <Text type="secondary">Story ID: {this.state.story.id}</Text> <br/> <br/>
                    <Row type="flex" justify="space-between">
                        <Col span={11}>
                            <div style={{
                                padding: '20px'
                            }}>
                                <List
                                    dataSource={this.state.story.tasks}
                                    renderItem={item => (
                                        <List.Item key={item.id}>
                                            <Checkbox checked={item.status} onChange={() => {
                                            }}>{item.name}</Checkbox>
                                        </List.Item>
                                    )}
                                >
                                    {this.state.loading && this.state.hasMore && (
                                        <div className="demo-loading-container">
                                            <Spin/>
                                        </div>
                                    )}
                                </List>

                            </div>
                        </Col>
                        <Col span={11}>
                            <Title level={4}>Documents</Title>
                            <List
                                dataSource={this.state.story.files}
                                renderItem={item => (
                                    <List.Item key={item.id}>
                                        <Button type="primary" shape="round"
                                                icon="file">{item.name}{item.extension}</Button>
                                    </List.Item>
                                )}
                            >
                                {this.state.loading && this.state.hasMore && (
                                    <div className="demo-loading-container">
                                        <Spin/>
                                    </div>
                                )}
                            </List>

                        </Col>
                    </Row>

                </Content>
                <Footer style={{textAlign: 'center'}}>Created by The Boys</Footer>
            </Layout>);
        } else {
            return <Fragment/>
        }
    }
}

export default TaskPage;
