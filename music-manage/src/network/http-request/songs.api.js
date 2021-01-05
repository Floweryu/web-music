import axios from '../http-instance/index'
import host from '../http-env/index'

const songs = {
  // 添加歌曲
  addSong(data) {
    return axios.post(`${host.apiHost}/admin/songs`, data)
  },
  // 更新歌曲
  updateSong(data) {
    return axios.put(`${host.apiHost}/admin/songs`, {}, data)
  },
  // 删除歌曲
  deleteSong(params) {
    return axios.delete(`${host.apiHost}/admin/songs`, params)
  },
  // 根据主键查询歌曲
  getSongById(params) {
    return axios.get(`${host.apiHost}/admin/song`, params)
  },
  // 获取所有歌曲
  getAllSongs() {
    return axios.get(`${host.apiHost}/admin/songs`)
  },
  // 根据歌名查询歌曲
  getSongsByName(params) {
    return axios.get(`${host.apiHost}/admin/songs/name`, params)
  },
  // 根据歌手姓名查询歌手
  getSongsBySingerName(params) {
    return axios.get(`${host.apiHost}/admin/songs/singer_name`, params)
  }
}

export default songs