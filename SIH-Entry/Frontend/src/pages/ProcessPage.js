import React, {Component, Fragment} from 'react';
import {Col, Input, Layout, Modal, Row, Typography} from 'antd';
import ProcessCard from '../components/cards/ProcessCard';
import axios from 'axios';
import PageHeader from '../components/layout/PageHeader';
import Urgent from '../components/sider/Urgent';

const {Title} = Typography;

const {Content, Footer} = Layout;

class ProcessPage extends Component {
    state = {
        visible: false,
        template: {},
        workflow: null,
        process: null,
        originalName: null
    };

    componentDidMount = async () => {
        const workflow = await axios
            .get(
                `http://localhost:8080/workflows/${this.props.match.params.workflowId}`
            )
            .then(response => response.data)
            .catch(error => console.log(error));
        this.setState({workflow: workflow});
    };

    showModal = () => {
        this.setState({visible: !this.state.visible});
    };

    handleOk = async e => {
        const {template} = this.state;
        template.name = template.name + " " + this.state.originalName;
        try {
            const response = await axios.post(
                `http://localhost:8080/workflows/${this.props.match.params.workflowId}/process`,
                template
            );

            this.setState({
                workflow: response.data,
                visible: false,
                template: {}
            });
        } catch (error) {
            console.log(error);
        }
    };

    handleCancel = e => {
        this.setState({
            visible: false
        });
    };

    async componentDidUpdate(prevProps, prevState) {
        try {
            if (
                this.state.visible !== prevState.visible &&
                this.state.visible === true
            ) {
                const response = await axios.get(
                    `http://localhost:8080/workflows/${this.props.match.params.workflowId}/process`
                );
                this.setState({template: response.data, originalName: response.data.name});
            }
        } catch (error) {
            console.log(error);
        }
    }

    onInputNameChange = e => {
        const {template} = this.state;
        template.name = e.target.value;
        this.setState({template});
    };

    render() {
        if (this.state.workflow) {
            return (
                <Fragment>
                    <Layout>
                        <PageHeader
                            button="Create Process"
                            onClick={this.showModal}
                            search="Search Process"
                        />
                        <Content style={{padding: 24}}>
                            <Row type="flex" justify="space-between">
                                <Col span={18}>
                                    <Title level={2}>Processes</Title>
                                    <Fragment>
                                        {this.state.workflow.processes.map(
                                            process => {
                                                return (
                                                    <ProcessCard
                                                        data={process}
                                                    />
                                                );
                                            }
                                        )}
                                    </Fragment>
                                </Col>
                                <Col
                                    span={6}
                                    style={{
                                        background: 'white',
                                        height: '100vh',
                                        borderLeft: '1px solid rgba(0,0,0,0.1)'
                                    }}
                                >
                                    <Urgent/>
                                </Col>
                            </Row>
                        </Content>
                        <Footer style={{textAlign: 'center'}}>
                            Created by The Boys
                        </Footer>
                    </Layout>

                    <Modal
                        title="Create Process"
                        visible={this.state.visible}
                        onCancel={this.handleCancel}
                        onOk={this.handleOk}
                    >
                        <Input
                            placeholder={this.state.template.name}
                            onChange={this.onInputNameChange}
                        />
                    </Modal>
                </Fragment>
            );
        } else {
            return <Fragment/>;
        }
    }
}

export default ProcessPage;
