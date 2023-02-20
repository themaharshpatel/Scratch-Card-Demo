package dev.maharshpatel.scratchcard.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.maharshpatel.scratchcard.data.ScratchCard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ScratchResultViewModel : ViewModel() {


    private val _scratchCards: MutableStateFlow<Map<Int, ScratchCard>> =
        MutableStateFlow(LinkedHashMap())
    val scratchCards = _scratchCards.asStateFlow().map {
        it.values.toList()
    }


    fun generateCards(count: Int) = viewModelScope.launch {
        val cards = LinkedHashMap<Int, ScratchCard>()

        for (i in 1..count) {
            val value = if(i%2==0){
                15
            }else{
                10
            }
            cards[i] = ScratchCard(i, value, false)
        }
        _scratchCards.update {
            cards
        }
    }

    fun cardScratched(scratchCard: ScratchCard) = viewModelScope.launch {
        _scratchCards.update { map ->
            val updatedMap = map.toMutableMap()
            updatedMap.getOrDefault(scratchCard.id, null)?.let {
                updatedMap[scratchCard.id] = it.copy(isScratched = true)
            }
            updatedMap
        }
    }


}