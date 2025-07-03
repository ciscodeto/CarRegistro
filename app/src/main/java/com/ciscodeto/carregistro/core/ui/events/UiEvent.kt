package com.ciscodeto.carregistro.core.ui.events

import com.ciscodeto.carregistro.cars.presentation.model.CarUi

sealed class UiEvent {
    data object ShowDeleteConfirmation : UiEvent()
    data object ShowFormModal : UiEvent()
}