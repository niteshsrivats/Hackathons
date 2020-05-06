import React, {Component, Fragment} from 'react';
import {Button, Checkbox, Col, Form, Input, Modal, Row, Table} from 'antd';
import {Link} from 'react-router-dom';
import axios from 'axios';
import {url} from '../config/constants';

const layout = {
  labelCol: {span: 8},
  wrapperCol: {span: 16}
};
const tailLayout = {
  wrapperCol: {offset: 8, span: 16}
};

const columns = [
  {
    title: 'Name',
    dataIndex: 'name',
    key: 'name'
  },
  {
    title: 'ID',
    dataIndex: 'id',
    key: 'id'
  },
  {
    title: 'Billing',
    dataIndex: 'billing',
    key: 'billing'
  },
  {
    title: 'Action',
    key: 'action',
    render: (text, record) => {
      var projectUrl = `/projects/${record.id}`;
      return (
          <span>
          <Link to={projectUrl}>Visit</Link>
        </span>
      );
    }
  }
];

class ProjectsScreen extends Component {
  state = {
    visible: false,
    dataSource: [],
    accessToken: ''
  };

  showModal = () => {
    this.setState({
      visible: true
    });
  };

  handleOk = e => {
    console.log(e);
    this.setState({
      visible: false
    });
  };

  async componentDidMount() {
    const accessToken = await localStorage.getItem('accessToken');
    this.setState({accessToken});
    const response = await axios.get(url + `/users`, {
      headers: {Authorization: accessToken}
    });
    console.log(response);
    const userWithProjects = response.data;
    this.setState({dataSource: userWithProjects.projects});
  }

  onFinish = async values => {
    try {
      var arr = [];
      const map = {
        executionTime: 'EXECUTION_TIME',
        executionSpace: 'EXECUTION_SPACE',
        persistentCode: 'PERSISTENT_CODE'
      };
      const mapKeys = Object.keys(map);
      Object.keys(values).forEach(value => {
        if (mapKeys.includes(value) && values[value] === true) {
          arr.push(map[value]);
        }
      });

      const obj = {name: values['name'], projectProperties: arr};
      const response = await axios.post(url + `/projects`, obj, {
        headers: {Authorization: this.state.accessToken}
      });
      const {dataSource} = this.state;
      dataSource.push(response.data);
      this.setState({dataSource});
      console.log(response, dataSource);
    } catch (error) {
      console.log(error);
    }
  };

  onFinishFailed = errorInfo => {
    console.log('Failed:', errorInfo);
  };

  render() {
    return (
        <Fragment>
          <Row justify="end">
            <Col span={12}>
              <h2>Projects</h2>
            </Col>
            <Col span={12}>
              <Button type="primary" onClick={this.showModal}>
                Create Project
              </Button>
            </Col>
          </Row>
          <Table dataSource={this.state.dataSource} columns={columns}/>;
          <Modal
              title="Create Project"
              visible={this.state.visible}
              onOk={this.handleOk}
          >
            <Form
                {...layout}
                name="basic"
                initialValues={{remember: true}}
                onFinish={this.onFinish}
                onFinishFailed={this.onFinishFailed}
            >
              <Form.Item
                  label="Name"
                  name="name"
                  rules={[
                    {required: true, message: 'Please input your username!'}
                  ]}
              >
                <Input/>
              </Form.Item>

              <Form.Item
                  {...tailLayout}
                  name="executionTime"
                  valuePropName="checked"
              >
                <Checkbox>Execution Time</Checkbox>
              </Form.Item>

              <Form.Item
                  {...tailLayout}
                  name="executionSpace"
                  valuePropName="checked"
              >
                <Checkbox>Execution Space</Checkbox>
              </Form.Item>
              <Form.Item
                  {...tailLayout}
                  name="persistentCode"
                  valuePropName="checked"
              >
                <Checkbox>Persistent Code</Checkbox>
              </Form.Item>
              <Form.Item {...tailLayout}>
                <Button type="primary" htmlType="submit">
                  Submit
                </Button>
              </Form.Item>
            </Form>
          </Modal>
        </Fragment>
    );
  }
}

export default ProjectsScreen;
