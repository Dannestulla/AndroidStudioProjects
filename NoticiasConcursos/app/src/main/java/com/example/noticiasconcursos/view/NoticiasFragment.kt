package com.example.noticiasconcursos.viewimport android.net.ConnectivityManagerimport android.os.Buildimport android.os.Bundleimport android.util.Logimport android.view.LayoutInflaterimport android.view.Viewimport android.view.ViewGroupimport androidx.annotation.RequiresApiimport androidx.core.content.ContextCompat.getSystemServiceimport androidx.core.view.isInvisibleimport androidx.core.view.isVisibleimport androidx.fragment.app.Fragmentimport androidx.lifecycle.MutableLiveDataimport androidx.recyclerview.widget.LinearLayoutManagerimport com.example.noticiasconcursos.Rimport com.example.noticiasconcursos.databinding.FragmentNoticiasBindingimport com.example.noticiasconcursos.features.CardDataimport com.example.noticiasconcursos.features.NoticiasAdapterimport com.example.noticiasconcursos.features.NoticiasViewModelimport com.example.noticiasconcursos.features.NoticiasViewModel.Companion.errorTextimport com.example.noticiasconcursos.features.NoticiasViewModel.Companion.myLiveDataimport kotlinx.coroutines.CoroutineScopeimport kotlinx.coroutines.Dispatchers.Defaultimport kotlinx.coroutines.launchopen class NoticiasFragment : Fragment(R.layout.fragment_noticias) {    lateinit var viewModel: NoticiasViewModel    private var _binding: FragmentNoticiasBinding? = null    private val binding get() = _binding!!    private var mNoticiasAdapter = NoticiasAdapter()    override fun onCreateView(        inflater: LayoutInflater,        container: ViewGroup?,        savedInstanceState: Bundle?    ): View {        _binding = FragmentNoticiasBinding.inflate(inflater, container, false)        return binding.root    }    override fun onDestroyView() {        super.onDestroyView()        Log.e("view", "destroyed")        _binding = null    }    @RequiresApi(Build.VERSION_CODES.M)    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {        super.onViewCreated(view, savedInstanceState)        viewModel = (activity as NoticiasActivity).viewModel        checkNetworkStatus()        //binding.button.setOnClickListener { deleteAll() } // clean database        binding.recyclerview.apply {            adapter = mNoticiasAdapter            layoutManager = LinearLayoutManager(context.applicationContext)        }        myLiveData.observe(viewLifecycleOwner, { newData ->            mNoticiasAdapter.submitList(newData)            binding.progressBar.isInvisible = true        })        errorText.observe(viewLifecycleOwner, {newText ->            binding.textError.text = newText            binding.textError.isVisible        })    }    @RequiresApi(Build.VERSION_CODES.M)    private fun checkNetworkStatus() {        val connectivityManager = context?.let { getSystemService(it,ConnectivityManager::class.java) }        val currentNetwork = connectivityManager?.activeNetwork        if (currentNetwork == null){            viewModel.isNetworkAvaliable = false            }    }    private fun deleteAll() {        CoroutineScope(Default).launch { myLiveData = MutableLiveData<ArrayList<CardData>>() }    }}