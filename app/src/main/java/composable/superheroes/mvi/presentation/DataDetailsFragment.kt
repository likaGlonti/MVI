package composable.superheroes.mvi.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import composable.superheroes.mvi.Module
import composable.superheroes.mvi.databinding.DataDetailsFragmentBinding
import composable.superheroes.mvi.domain.DataEntity
import composable.superheroes.mvi.presentation.intent.DataIntent
import composable.superheroes.mvi.presentation.state.DataState
import composable.superheroes.mvi.presentation.vm.DataDetailsViewModel
import composable.superheroes.mvi.presentation.vm.factory.DataDetailsVMFactory

class DataDetailsFragment : Fragment() {

    private val viewModel by viewModels<DataDetailsViewModel> { DataDetailsVMFactory(Module.repository) }

    private lateinit var binding: DataDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = requireArguments().getInt("item_position")
        viewModel.intent(DataIntent.EditDataItem(args))
        with(binding) {
            save.setOnClickListener {
                viewModel.intent(
                    DataIntent.SaveData(
                        DataEntity(
                            title = title.text.toString(),
                            description = description.text.toString()
                        ), index = args
                    )
                )
                parentFragmentManager.popBackStack()
            }

            viewModel.state.observe(viewLifecycleOwner) {
                when (it) {
                    is DataState.Success -> {
                        title.setText(it.data.title)
                        description.setText(it.data.description)
                    }
                    is DataState.Error -> {
                        Toast.makeText(requireContext(), "Error Loading data!", Toast.LENGTH_SHORT)
                            .show()
                    }
                    is DataState.Loading -> {

                    }
                }

            }
        }
    }

}