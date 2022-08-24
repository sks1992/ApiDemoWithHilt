package sk.sandeep.demoapidagger

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import sk.sandeep.demoapidagger.adapter.DemoAdapter
import sk.sandeep.demoapidagger.databinding.ActivityMainBinding
import sk.sandeep.demoapidagger.view_model.MainActivityViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerViewAdapter: DemoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initializeRecyclerView(binding.rvDemo)
        initViewModel()
    }

    /** override onDestroy $ set binding to Null*/
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun initializeRecyclerView(rv: RecyclerView) {
        rv.layoutManager = LinearLayoutManager(this)
        recyclerViewAdapter = DemoAdapter()
        rv.adapter = recyclerViewAdapter
    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        viewModel.getLiveDataObserver().observe(this) {
            if (it != null) {
                recyclerViewAdapter.setListData(it)
                recyclerViewAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "error in getting data", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.loadListOfData()
    }
}