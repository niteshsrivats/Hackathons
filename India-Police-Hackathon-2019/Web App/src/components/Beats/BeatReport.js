import React, { Component, Fragment } from 'react';
import axios from 'axios';
import {
    Row,
    Col,
    List,
    Descriptions,
    Divider
    // Tag
} from 'antd';
import { Link } from 'react-router-dom';

class BeatReport extends Component {
    state = { beatReport: {beatExpectationsReports:[], beatIllegalActivitiesReports: [], 
        beatLawOrderIssuesReports: [], crimes: []} };

    componentWillMount = async () => {

        var id =  this.props.match.params.reportid;
        const beatReportResponse = await axios.get(
            `http://172.16.8.174:8080/v1/beat-reports/${id}`
        );
        const beatReport = beatReportResponse.data;
        this.setState({beatReport});
        console.log(beatReport);
    };
    render() {
        return (
            <Fragment>
            {/* <Divider style="vertical"/> */}
                <Row>
                <Col xs={2} sm={4} md={6} lg={8} xl={6}></Col>
                <Col xs={20} sm={16} md={12} lg={8} xl={12}>
                  
                {this.state.beatReport.beatExpectationsReports.map((item) => {
                    return (
                        <Fragment>
<Divider/>
<Descriptions title="Beat Expectations Report">
                        <Descriptions.Item label="Id">{item.id}</Descriptions.Item>
                        <Descriptions.Item label="Name">{item.name}</Descriptions.Item>
                        <Descriptions.Item label="Telephone">{item.phoneNumber}</Descriptions.Item>
                        <Descriptions.Item label="Type">{item.beatExpectationsReports}</Descriptions.Item>
                        <Descriptions.Item label="Description">{item.description}</Descriptions.Item>
                    </Descriptions>
                    <Divider/>
                        </Fragment>
                    );
                })}
                {this.state.beatReport.beatIllegalActivitiesReports.map((item) => {
                        

                    return (<Fragment><Divider/><Descriptions title="Beat Illegal Activities Report">
                        <Descriptions.Item label="Id">{item.id}</Descriptions.Item>
                        <Descriptions.Item label="Type">{item.beatIllegalActivitiesType}</Descriptions.Item>
                        <Descriptions.Item label="Description">{item.description}</Descriptions.Item>
                    </Descriptions>               <Divider/>
                        </Fragment>);
                })}
                {this.state.beatReport.beatLawOrderIssuesReports.map((item) => {

                    return (                        <Fragment>
                        <Divider/><Descriptions title="Beat Expectations Report">
                        <Descriptions.Item label="Id">{item.id}</Descriptions.Item>
                        <Descriptions.Item label="Type">{item.beatLawOrderIssueType}</Descriptions.Item>
                        <Descriptions.Item label="Description">{item.description}</Descriptions.Item>
                    </Descriptions>               <Divider/>
                        </Fragment>);
                })}
                {this.state.beatReport.crimes.map((item) => {
           
                    return (             <Fragment>
                        <Divider/><Descriptions title="Beat Expectations Report">
                        <Descriptions.Item label="Id">{item.id}</Descriptions.Item>
                        <Descriptions.Item label="Beat">{item.beatId}</Descriptions.Item>
                        <Descriptions.Item label="Type">{item.crimeType}</Descriptions.Item>
                        <Descriptions.Item label="Time">{item.time}</Descriptions.Item>
                        <Descriptions.Item label="Description">{item.description}</Descriptions.Item>
                    </Descriptions>               <Divider/>
                        </Fragment>);
                })}

</Col>
                    <Col xs={2} sm={4} md={6} lg={8} xl={6}></Col>
                </Row>
            {/* <Divider style={type }/> */}
            </Fragment>
            )
        }
};
export default BeatReport;
