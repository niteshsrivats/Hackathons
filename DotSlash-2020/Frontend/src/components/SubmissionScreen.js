import React, {Component, Fragment} from 'react';
import {Col, Row, Statistic, Table} from 'antd';
import axios from 'axios';

import {url} from '../config/constants';

// const submission = {
//   id: "21244",
//   compiler: "python",
//   code: "a,b = input(), input()\n print(a+b)",
//   stats: {
//     input: "5,7",
//     output: "12",
//     runtime: 3,
//     memory: 30,
//     success: true
//   }
// };

const columns = [
  {
    title: 'Input',
    dataIndex: 'input',
    key: 'input'
  },
  {
    title: 'Output',
    dataIndex: 'output',
    key: 'output'
  },
  {
    title: 'Success',
    dataIndex: 'success',
    key: 'success'
  },
  {
    title: 'Runtime(ms)',
    dataIndex: 'runTime',
    key: 'runTime'
  },
  {
    title: 'Memory(mb)',
    dataIndex: 'memory',
    key: 'memory'
  },

  {
    title: 'Time(unix Timestamp)',
    dataIndex: 'time',
    key: 'time'
  }
];

class SubmissionScreen extends Component {
  state = {
    submission: ''
  };

  async componentDidMount() {
    const accessToken = await localStorage.getItem('accessToken');
    const submission = await axios.get(
        url + `/submissions/${this.props.match.params.id}`,
        {headers: {Authorization: accessToken}}
    );
    this.setState({submission: submission.data});
  }

  render() {
    console.log(this.state.submission);
    const {id, compiler, code, stats} = this.state.submission;
    return (
        <Fragment>
          <h2>Submission #{id}</h2>
          <Row gutter={2} style={{marginBottom: 8}}>
            <Col
                span={10}
                style={{backgroundColor: 'white', padding: 16, marginRight: 20}}
            >
              <Statistic title="Compiler" value={compiler}/>
            </Col>
          </Row>
          <Table dataSource={stats} columns={columns}/>

          <div style={{marginTop: 16}}>
            <div
                style={{
                  backgroundColor: 'white',
                  boxShadow: '1px 1px #888888',
                  minWidth: 200,
                  minHeight: 200,
                  padding: 16
                }}
            >
              <pre>{code}</pre>
            </div>
          </div>
        </Fragment>
    );
  }
}

export default SubmissionScreen;
