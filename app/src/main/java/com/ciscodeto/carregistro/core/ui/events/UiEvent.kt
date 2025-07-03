package com.ciscodeto.carregistro.core.ui.events

sealed class UiEvent {
    data object ShowConfirmationModal : UiEvent()
    data object ShowFormModal : UiEvent()
    data object CloseFormModal : UiEvent()
}