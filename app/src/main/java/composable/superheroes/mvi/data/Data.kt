package composable.superheroes.mvi.data


object Data {
    private val dataArray = arrayListOf(
        DataDTO("lika", "glonti"),
        DataDTO(
            "What is Lorem Ipsum?",
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                    "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when" +
                    " an unknown printer took a galley of type and scrambled it to make a type specimen book." +
                    " It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. " +
                    "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with" +
                    " desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum"
        ),
        DataDTO(
            "Why do we use it?",
            "It is a long established fact that a reader will be distracted by " +
                    "the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it " +
                    "has a more-or-less normal distribution of letters," +
                    " as opposed to using 'Content here, content here', making it look like readable English"
        ), DataDTO(
            "Where does it come from?",
            "Contrary to popular belief, Lorem Ipsum is not simply random text. " +
                    "It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock," +
                    " a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur," +
                    " from a Lorem Ipsum passage," +
                    " and going through the cites of the word in classical literature, discovered the undoubtable source."
        )
    )

    fun addData(data: DataDTO) {
        dataArray.add(0, data)
    }

    fun deleteAt(index: Int) {
        dataArray.removeAt(index)
    }

    fun getAll() = dataArray

    fun get(index: Int) = dataArray[index]
}