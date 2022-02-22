package composable.superheroes.mvi.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import composable.superheroes.mvi.Module
import composable.superheroes.mvi.R
import composable.superheroes.mvi.databinding.DataListFragmentBinding
import composable.superheroes.mvi.domain.DataEntity
import composable.superheroes.mvi.presentation.intent.DataIntent
import composable.superheroes.mvi.presentation.state.DataState
import composable.superheroes.mvi.presentation.vm.DataListViewModel
import composable.superheroes.mvi.presentation.vm.factory.DataListVMFactory

class DataListFragment : Fragment() {

    private val viewModel by viewModels<DataListViewModel> { DataListVMFactory(Module.repository) }

    private lateinit var binding: DataListFragmentBinding
    private lateinit var adapter: DataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = DataAdapter()
        adapter.getPosition = { position ->

            val bundle = bundleOf("item_position" to position)
            parentFragmentManager.commit {
                replace<DataDetailsFragment>(R.id.fragment_container_view, args = bundle)
                setReorderingAllowed(true)
                addToBackStack(null)
            }

        }

        viewModel.intent(DataIntent.GetAll)

        with(binding) {
            dataRecycler.layoutManager = LinearLayoutManager(context)
            dataRecycler.adapter = adapter
            viewModel.state.observe(viewLifecycleOwner) {
                when (it) {
                    is DataState.Success<List<DataEntity>> -> {
                        adapter.submitList(it.data)
                    }
                    is DataState.Loading -> {
                    }
                    is DataState.Error -> {
                        Toast.makeText(requireContext(), "Error Loading data!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }
}