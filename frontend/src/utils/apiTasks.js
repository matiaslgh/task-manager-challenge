const tasksEndPoint = 'http://localhost:8080/tasks'

const headers = {
  'Accept': 'application/json',
  'Content-Type': 'application/json'
}

const request = method => (path='', body) => {
  const config = { headers, method }
  if ( body ) {
    config.body = JSON.stringify(body)
  }
  return fetch(tasksEndPoint + path, config)
    .then(res => res.text())
    .then(text => text ? JSON.parse(text) : {})
    .then(data => {
      if (data.errorMessage) {
        throw Error(data.errorMessage)
      }
      return data
    })
}

const get = request('GET')

const post = (path, body) => request('POST')(path, body)

export const createTask = number => post('/',{number})

export const getTasks = () => get()

export const runTask = id => post('/run', {id})

export const getStatuses = () => get('/statuses')