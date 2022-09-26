import java.io.File

val wordList = File("dictionary.txt").readLines()

val lengthToWordMap = wordList.groupBy { word -> word.length }

val composedWordLength = 6

// O(LN^3)
(1 until composedWordLength).forEach { firstWordLength ->
  val secondWordLength = composedWordLength - firstWordLength
  
  // O(N)
  val firstWordCandidates = lengthToWordMap[firstWordLength] ?: emptyList<String>()
  val secondWordCandidates = lengthToWordMap[secondWordLength] ?: emptyList<String>()
  val composedWordCandidates = lengthToWordMap[composedWordLength] ?: emptyList<String>()

  // O(N^2)
  val composedWordStartsWithFirstWords = firstWordCandidates.map { firstWord ->
    val candidate = composedWordCandidates
      .filter { it.startsWith(firstWord) }
      .toSet()
    Pair(firstWord, candidate)
  }

  // O(N^2)
  val composedWordEndsWithSecondWords = secondWordCandidates.map { secondWord ->
    val candidate = composedWordCandidates
      .filter { it.endsWith(secondWord) }
      .toSet()
    Pair(secondWord, candidate)
  }

  // O(N^3)
  composedWordStartsWithFirstWords.forEach { (firstWord, cand1) ->
    composedWordEndsWithSecondWords.forEach { (secondWord, cand2) ->
      val commonWord = cand1 intersect cand2
      if (commonWord.size == 1) {
        println("$firstWord + $secondWord => ${firstWord + secondWord}")
      }
    }
  }
}

