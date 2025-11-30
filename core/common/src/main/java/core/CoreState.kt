package core

class CoreState {
    interface ViewState{
        val isLoading: Boolean
    }
    interface SideEffect
    interface Event
}