import React, {Component, Fragment} from 'react';
import {Card, Col, Row} from 'antd';
import {Column, Line} from '@antv/g2plot';
import axios from 'axios';
import {url} from '../config/constants';

const {Meta} = Card;


const requestData = [
  {date: '29th', value: 5},
  {date: '30th', value: 6},
  {date: '1st', value: 7},
  {date: '2nd', value: 8},
  {date: '3rd', value: 8},
  {date: '4th', value: 4},
  {date: '5th', value: 11},
  {date: '6th', value: 17},
  {date: '7th', value: 21},
];

class DashboardScreen extends Component {
  containerRef = React.createRef();
  requestsRef = React.createRef();
  pricesRef = React.createRef();

  state = {user: {projects: []}, totalRequests: 0, totalPrice: 0, totalCompute: 0};

  getBill(requests, projectProperties) {
    const pricePerRequest = 0.2 + projectProperties.length * 0.5;
    return pricePerRequest * requests;
  }

  async componentDidMount() {
    const accessToken = await localStorage.getItem('accessToken');
    const response = await axios.get(url + `/users`, {
      headers: {Authorization: accessToken}
    });
    this.setState({user: response.data});

    const node = this.containerRef.current;
    const data = [];
    const pricesData = [];

    let priceSum = 0;
    let totalExecutionTime = 0;
    let sum = 0;
    for (let i = 0; i < response.data.projects.length; i++) {
      const project = response.data.projects[i];
      data.push({project: project.name, requests: project.metric.requests});
      // eslint-disable-next-line eqeqeq
      if (project.metric.executionTime != undefined) {
        totalExecutionTime += project.metric.executionTime;
      }
      if (project.metric.requests !== undefined) {

        sum += project.metric.requests;
      }

      let price = this.getBill(project.metric.requests, project.projectProperties);
      priceSum += price;
      pricesData.push({project: project.name, price: price});
    }
    this.setState({totalRequests: sum, totalPrice: priceSum, totalCompute: totalExecutionTime});

    const projectRequests = new Column(node, {
      data,
      xField: 'project',
      yField: 'requests',
      height: 200,
      width: 300,
    });
    projectRequests.render();

    const requestsNode = this.requestsRef.current;
    const requestsLine = new Line(requestsNode, {
      data: requestData,
      xField: 'date',
      yField: 'value',
      width: 900,
      height: 200,
    });

    requestsLine.render();
    const pricesNode = this.pricesRef.current;
    const pricesBar = new Column(pricesNode, {
      data: pricesData,
      xField: 'project',
      yField: 'price',
      width: 300,
      height: 200,
    });
    pricesBar.render();
  }

  render() {
    return (
        <Fragment>
          <h2>Dashboard</h2>
          <Row>
            <Col span={12}>
              <Card style={{marginRight: 10}}>
                <p>Project Wise Usages</p>
                <h3 style={{fontSize: 54, color: 'orange'}}>
                  {this.state.totalRequests} Requests
                  <span style={{fontSize: 14, color: 'rgba(0,0,0,0.65)'}}>
                  {' '}
                    this month
                </span>
                </h3>
                <div id="container" ref={this.containerRef}></div>
                <Meta title="Average Daily Usage" description="1.5MB"/>
              </Card>
            </Col>
            <Col span={12}>
              <Card style={{minHeight: 450}}>
                <p>March 2020</p>
                <h3 style={{fontSize: 54, color: 'orange'}}>&#8377;{this.state.totalPrice}</h3>
                <div id="prices" ref={this.pricesRef}></div>
                <Meta title="Today's amount" description="&#8377; 3"/>
              </Card>
            </Col>
          </Row>
          <br/>
          <Row>
            <Col span={24}>
              <Card>
                <p>Compute Time</p>
                <h3 style={{fontSize: 54, color: 'orange'}}>

                  {this.state.totalCompute / 1000}s
                  <span style={{fontSize: 14, color: 'rgba(0,0,0,0.65)'}}>
                  {' '}
                    this month
                </span>
                </h3>
                <div id="requests" ref={this.requestsRef}></div>
                <Meta title="Average Daily Requests" description="8"/>
              </Card>
            </Col>
          </Row>
        </Fragment>
    );
  }
}

export default DashboardScreen;
