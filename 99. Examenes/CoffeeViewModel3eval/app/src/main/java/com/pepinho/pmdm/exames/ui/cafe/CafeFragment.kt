�� sr 1com.android.builder.dexing.MutableDependencyGraph        L dependentsMapt Ljava/util/Map;xpsr java.util.LinkedHashMap4�N\l�� Z accessOrderxr java.util.HashMap���`� F 
loadFactorI 	thresholdxp?@      w       x                                                                                                                                                                                                                                                                                      port com.pepinho.pmdm.exames.model.setImageFromBytes
import com.pepinho.pmdm.exames.repository.CafeRepository
import kotlinx.coroutines.launch

class CafeFragment : Fragment() {

    private var _binding: FragmentCafeBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "No puede acceder al binding porque es nulo. ¿Está visible la vista?"
        }

    private val args: CafeFragmentArgs by navArgs()

    private val viewModel: CoffeeViewModel by viewModels {
//        val repository = (requireActivity().application as CafeApplication).repository
//        CoffeeViewModelFactory(repository)
        CoffeeViewModelFactory(CafeRepository.get())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCafeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("JuegoFragment", "idJuego recibido: ${args.idCafe}")

        viewModel.getCafeConCategoriaById(args.idCafe)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadingStateCafe.collect { loading ->
                if (loading) {
                    binding.progressBar?.visibility = View.VISIBLE
                } else {
                    binding.progressBar?.visibility = View.GONE
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.cafeConCategoria.collect { cafe ->
                cafe.let {
                    binding.tvNombre.text = it.cafe.nombre
                    binding.tvCalorias.text = it.cafe.calorias.toString()
                    binding.imCafe.setImageFromBytes(it.cafe.foto)
                    binding.tvTipo.text = it.cafe.tipo
                    binding.tvDescripcion.text = it.cafe.descripcion
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}