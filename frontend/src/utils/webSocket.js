import SockJS from 'sockjs-client'
import Stomp from 'stompjs'

const SOCKET_URL = 'http://localhost:8080/websocket'

export const connectWebSocket = () => {
  const socket = new SockJS(SOCKET_URL)
  return Stomp.over(socket)
}