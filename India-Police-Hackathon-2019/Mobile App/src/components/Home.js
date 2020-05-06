import React, {Fragment, Component} from 'react';
import {
  Alert,
  AsyncStorage,
  View,
  Text,
  Button,
  PermissionsAndroid,
} from 'react-native';
import axios from 'axios';
import SmsListener from 'react-native-android-sms-listener';

const otpUrl = 'http://172.16.8.185:4060/api';

class Home extends Component {
  state = {
    otps: [],
    correctOtps: 5,
  };

  handleOtps = async () => {
    const response = await axios.get(otpUrl + '/getotps');
    var {correctOtps} = this.state;
    console.log(response);
    if (response.data.otps) {
      var incomingOtps = response.data.otps;
      var {otps} = this.state;
      var newOtps = otps.concat(incomingOtps);
      console.log('lol', newOtps);
      this.setState({otps: newOtps});
    }
    while (correctOtps > 3) {
      await axios.get(otpUrl + '/sendotp');
      correctOtps -= 1;
    }
    // this.setState({correctOtps: 5});
  };

  // componentDidMount() {
  //   this.requestReadSmsPermission();
  //   setInterval(this.handleOtps, 10000);

  //   SmsListener.addListener(message => {
  //     const {otps} = this.state;
  //     console.log(otps);
  //     console.log(message);
  //     if (otps) {
  //       if (message.body.length >= 6) {
  //         var smsOtp = message.body.slice(-6);
  //         if (smsOtp in otps) {
  //           console.log('Correct otp');
  //           nextOtpState = otps.filter(element => element !== smsOtp);
  //           this.setState({otps: nextOtpState});
  //         }
  //       }
  //     }
  //   });
  // }

  // requestReadSmsPermission = async () => {
  //   try {
  //     var granted = await PermissionsAndroid.request(
  //       PermissionsAndroid.PERMISSIONS.READ_SMS,
  //       {
  //         title: 'Auto Verification OTP',
  //         message: 'need access to read sms, to verify OTP',
  //       },
  //     );
  //     if (granted === PermissionsAndroid.RESULTS.GRANTED) {
  //       console.log('sms read permissions granted', granted);
  //       granted = await PermissionsAndroid.request(
  //         PermissionsAndroid.PERMISSIONS.RECEIVE_SMS,
  //         {
  //           title: 'Receive SMS',
  //           message: 'Need access to receive sms, to verify OTP',
  //         },
  //       );
  //       if (granted === PermissionsAndroid.RESULTS.GRANTED) {
  //         console.log('RECEIVE_SMS permissions granted', granted);
  //       } else {
  //         console.log('RECEIVE_SMS permissions denied');
  //       }
  //     } else {
  //       console.log('sms read permissions denied');
  //     }
  //   } catch (err) {
  //     console.log(err);
  //   }
  // };

  handleEndBeat = async () => {
    try {
      const beatToken = await AsyncStorage.getItem('beatToken');
      console.log(beatToken);
      const response = await axios.patch(
        `http://172.16.8.174:8080/v1/beat-reports/${beatToken}/end`,
      );
      console.log(response);
      await AsyncStorage.removeItem('beatToken');
      this.props.navigation.navigate('AuthLoading');
    } catch (err) {
      console.log(err);
      Alert.alert('Either the API call or token removal failed');
    }
  };

  render() {
    const {navigate} = this.props.navigation;
    return (
      <Fragment>
        <View style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
          <View style={{padding: 20, width: 300}}>
            <Button
              style={{}}
              title="Expectation Report"
              onPress={() => navigate('ExpectationReport')}
            />
          </View>
          <View style={{padding: 20, width: 300}}>
            <Button
              style={{}}
              title="Illegal Activity Report"
              onPress={() => navigate('IllegalActivityReport')}
            />
          </View>
          <View style={{padding: 20, width: 300}}>
            <Button
              style={{}}
              title="Law and Order Report"
              onPress={() => navigate('LawAndOrderReport')}
            />
          </View>
          <View style={{marginVertical: 50, padding: 20, width: 300}}>
            <Button title="End Beat" onPress={() => this.handleEndBeat()} />
          </View>
        </View>
      </Fragment>
    );
  }
}

export default Home;
