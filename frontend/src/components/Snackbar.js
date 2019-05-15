import React from 'react'
import { connect } from 'react-redux'
import { closeSnackbar } from '../actions/uiAction'
import { Snackbar as SnackbarMI } from 'material-ui'

const CLOSE_TIME_IN_MILLIS = 5000

const Snackbar = props => {
  const { closeSnackbar, isOpen, message } = props

  if (isOpen) {
    setTimeout(closeSnackbar, CLOSE_TIME_IN_MILLIS)
  }

  return (
    <SnackbarMI
      anchorOrigin={{ vertical: 'bottom', horizontal: 'center' }}
      open={isOpen}
      SnackbarContentProps={{
        'aria-describedby': 'message-id',
      }}
      message={<span id="message-id">{message}</span>}
    />
  )
}

const mapStateToProps = ({ ui }) => ({
  isOpen: ui.snackbar.isOpen,
  message: ui.snackbar.message
})

const mapDispatchToProps = dispatch => ({
  closeSnackbar: () => dispatch(closeSnackbar())
})

export default connect(mapStateToProps, mapDispatchToProps)(Snackbar)