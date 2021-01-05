import axios from '../http-instance/index'
import host from '../http-env/index'

const user = {
  //添加用户
  addUser(data) {
    return axios.post(`${host.apiHost}/admin/user`, data)
  },
  getAllUsers() {
    return axios.get(`${host.apiHost}/admin/users`)
  }
}

export default user
