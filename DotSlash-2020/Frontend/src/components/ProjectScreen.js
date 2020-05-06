import React, {Component, Fragment} from 'react';
import {Button, Card, Col, Input, Row, Table} from 'antd';
import {Link} from 'react-router-dom';
import {Line} from '@antv/g2plot';
import axios from 'axios';
import {url} from '../config/constants';

const {Meta} = Card;

const columns = [
  {
    title: 'ID',
    dataIndex: 'id',
    key: 'id'
  },
  {
    title: 'Action',
    key: 'action',
    render: (text, record) => {
      var submissionUrl = `/submissions/${record.id}`;
      return (
          <span>
          <Link to={submissionUrl}>Visit</Link>
        </span>
      );
    }
  }
];

class ProjectScreen extends Component {
  avgExecsRef = React.createRef();
  requestsRef = React.createRef();
  // average execution time, no.of requests
  state = {
    project: '',
    apiId: '',
    generatedAPIKey: '',
    dataSource: [], // submissions
    avgExecData: [],
    avgExecTime: 0,
    bill: 0
  };

  getBill(requests, projectProperties) {
    const pricePerRequest = 0.2 + projectProperties.length * 0.5;
    return pricePerRequest * requests;
  }

  async componentDidMount() {
    const accessToken = await localStorage.getItem('accessToken');
    const project = await axios.get(
        url + `/projects/${this.props.match.params.id}`,
        {headers: {Authorization: accessToken}}
    );

    this.setState({bill: this.getBill(project.data.metric.requests, project.data.projectProperties)});
    const submissions = await axios.get(
        url + `/projects/${this.props.match.params.id}/submissions`,
        {headers: {Authorization: accessToken}}
    );
    this.setState({
      dataSource: submissions.data,
      project: project.data,
      apiId: project.data.apiId
    });

    const {dataSource} = this.state;
    if (dataSource.length !== 0) {
      const avgExecData = dataSource.map((item, index) => {
        return {requests: index, value: item.stats[0].runTime};
      });
      var avgExecTime = 0;
      for (let i = 0; i < avgExecData.length; i++) {
        avgExecTime += avgExecData[i].value;
      }
      this.setState({
        avgExecData,
        avgExecTime: (avgExecTime / avgExecData.length).toFixed(2)
      });

      const avgExecsNode = this.avgExecsRef.current;
      const avgExecsLine = new Line(avgExecsNode, {
        data: this.state.avgExecData,
        xField: 'requests',
        yField: 'value',
        width: 500,
        height: 200
      });
      avgExecsLine.render();

      let priceData = [];

      for (let i = 0; i < this.state.dataSource.length; i++) {
        const stats = this.state.dataSource[i].stats;
        for (let j = 0; j < stats.length; j++) {
          console.log(stats[j]);
          const date = new Date(stats[j].time);
          const minutes = '0' + date.getMinutes();
          const seconds = '0' + date.getSeconds();
          priceData.push({minute: minutes, price: seconds * 2 / 3});
        }
      }

      const requestsNode = this.requestsRef.current;
      const requestsLine = new Line(requestsNode, {
        data: priceData,
        xField: 'minute',
        yField: 'price',
        width: 500,
        height: 200
      });
      requestsLine.render();
    }
  }

  generateAPIKey = () => {
  };

  render() {
    const {id} = this.props.match.params;
    return (
        <Fragment>
          <h1>Project Name</h1>
          <span style={{fontSize: 32}}>#{id}</span>
          <p>Use the button below to generate an API key.</p>
          <Button type="primary" onClick={this.generateAPIKey}>
            Generate
          </Button>
          <br/>
          <br/>
          {this.state.apiId && <Input placeholder={this.state.apiId} disabled/>}
          <br/>
          <br/>
          <Row>
            <Col span={12}>
              <Card style={{marginRight: 10}}>
                <p>Average Execution Time</p>
                <h3 style={{fontSize: 54, color: 'orange'}}>
                  {this.state.avgExecTime}ms
                </h3>
                <div id="avgExecsRef" ref={this.avgExecsRef}></div>
              </Card>
            </Col>
            <Col span={12}>
              <Card>
                <p>March 2020</p>
                <h3 style={{fontSize: 54, color: 'orange'}}>&#8377;{this.state.bill}</h3>
                <div id="requestsRef" ref={this.requestsRef}></div>
                <Meta title="Today's amount" description="&#8377; 3"/>
              </Card>
            </Col>
          </Row>
          <br/>
          <h2>Submissions</h2>
          <Table dataSource={this.state.dataSource} columns={columns}/>;
        </Fragment>
    );
  }
}

export default ProjectScreen;
