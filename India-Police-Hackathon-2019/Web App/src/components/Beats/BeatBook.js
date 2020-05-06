import React, { Component, Fragment } from 'react';
import axios from 'axios';
import {
    Row,
    Col,
    List
    // Tag
} from 'antd';
import { Link } from 'react-router-dom';

class BeatBook extends Component {

    state = {beatReports: []};
    componentDidMount = async () => {
        const beatBookresponse = await axios.get(
            `http://172.16.8.174:8080/v1/beat-books/beat/${this.props.id}`
        );
        this.setState({id: this.props.id})
        const beatReports = beatBookresponse.data.beatReports;
        this.setState({beatReports});
    }

    render() {
        return (
            <Row>
                <Col xs={2} sm={4} md={6} lg={8} xl={6}></Col>
                <Col xs={20} sm={16} md={12} lg={8} xl={12}>
                    <List
                        itemLayout="horizontal"
                        dataSource={this.state.beatReports}
                        renderItem={(item) => {
                            const beatLink = `/report/${item.id}`;
                            return (
                                <Link to={beatLink}>
                                    <List.Item>
                                        <List.Item.Meta
                                            description={
                                                'Duration: ' +
                                                ((item.endTime - item.startTime) / 1000)
                                            }
                                            title={'Report #' + item.id}
                                        />
                                    </List.Item>
                                </Link>
                            );
                        }}
                    />
                </Col>
                <Col xs={2} sm={4} md={6} lg={8} xl={6}></Col>
            </Row>
        );
    }
}
export default BeatBook;
