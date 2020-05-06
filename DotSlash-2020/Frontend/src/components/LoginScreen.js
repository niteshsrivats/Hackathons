import React from 'react';
import {Button, Form, Input, notification} from 'antd';
import axios from 'axios';

import {url} from '../config/constants';

const layout = {
  labelCol: {span: 8},
  wrapperCol: {span: 16}
};
const tailLayout = {
  wrapperCol: {offset: 8, span: 16}
};

const LoginScreen = (props) => {
  const onFinish = async values => {
    try {
      const response = await axios.post(
          url + `/users/login`,
          values
      ); // VRWatYWP
      const {accessToken} = response.data;
      await localStorage.setItem('accessToken', accessToken);
      notification.open({
        message: 'Login successful.',
        description:
            'You will be redirected shortly.',
        onClick: () => {
        }
      });
      return setTimeout(() => {
        props.history.push('/');
      }, 1500);
    } catch (error) {
      console.log(error);
    }
  };

  const onFinishFailed = errorInfo => {
    console.log('Failed:', errorInfo);
  };

  return (
      <Form
          {...layout}
          name="basic"
          initialValues={{remember: true}}
          onFinish={onFinish}
          onFinishFailed={onFinishFailed}
      >
        <Form.Item
            label="Username"
            name="username"
            rules={[{required: true, message: 'Please input your username!'}]}
        >
          <Input/>
        </Form.Item>

        <Form.Item
            label="Password"
            name="password"
            rules={[{required: true, message: 'Please input your password!'}]}
        >
          <Input.Password/>
        </Form.Item>

        <Form.Item {...tailLayout}>
          <Button type="primary" htmlType="submit">
            Submit
          </Button>
        </Form.Item>
      </Form>
  );
};

export default LoginScreen;
