import React, {Component, Fragment} from "react";
import {Col, Layout, Row, Typography} from "antd";
import DriveCard from "../components/cards/DriveCard"
import axios from "axios";
import PageHeader from "../components/layout/PageHeader";

const {Title} = Typography;

const {Content, Footer} = Layout;

class Drive extends Component {
    state = {
        data: null,
        previousId: 0,
        currentData: null,
        lol: null,
        level: 0
    };

    componentDidMount = async () => {
        const data = await axios.get(`http://localhost:8080/oldHabits`)
            .then((response) => response.data)
            .catch((error) => console.log(error));
        this.setState({data: data, currentData: data});
    };

    handleChange = id => {
        this.state.currentData.map((item) => {
            if (item["id"] === id) {
                if (this.state.level === 0) {
                    this.setState({currentData: item["processes"], level: 1, previousId: id});
                } else if (this.state.level === 1) {
                    this.setState({currentData: item["stories"], level: 2, previousId: id});
                } else if (this.state.level === 2) {
                    this.setState({currentData: item["tasks"], level: 3, previousId: id, lol: item.files});
                } else if (this.state.level === 3) {
                    this.setState({currentData: item["files"], level: 4, previousId: id});
                }
            }
        });
    };


    render() {
        if (this.state.currentData) {
            return (
                <Layout>
                    <PageHeader button="Add File"/>
                    <Content style={{margin: '0 16px'}}>
                        <Row type="flex" justify="space-between">
                            <Col span={18}>
                                <Title level={2}>Drive</Title>
                                <Fragment>
                                    {this.state.level <= 3 && this.state.currentData.map((data) => {
                                        return <DriveCard data={data} type={"folder"} handleChange={this.handleChange}/>
                                    })}
                                    {this.state.level === 3 && this.state.lol && this.state.lol.map((data) => {
                                            return <DriveCard data={data} type={"file"}/>
                                        }
                                    )}
                                    {this.state.level === 4 && this.state.currentData.map((data) => {
                                            return <DriveCard data={data} type={"file"}/>
                                        }
                                    )}
                                </Fragment>
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

export default Drive;