package carreiras.com.github.listadecompras

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import carreiras.com.github.listadecompras.adapter.ItemsAdapter
import carreiras.com.github.listadecompras.model.ItemModel
import carreiras.com.github.listadecompras.viewmodel.ItemsViewModel

/** A classe MainActivity é a atividade principal da aplicação. Ela estende ComponentActivity, que é uma classe base para atividades*/
class MainActivity : ComponentActivity() {
    /** A variável viewModel é uma instância da classe ItemsViewModel. O ViewModel é usado para armazenar e gerenciar dados relacionados à UI de maneira consciente ao ciclo de vida.*/
    val viewModel: ItemsViewModel by viewModels()

    /** Este método é chamado quando a atividade é criada. Ele contém a lógica de inicialização para a atividade.*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        /** O RecyclerView é um componente que exibe uma lista ou grade de itens. O ItemsAdapter é usado para fornecer as visualizações que representam os itens na lista.*/
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView);
        val itemsAdapter = ItemsAdapter()

        recyclerView.adapter = itemsAdapter

        /**O botão e o EditText são usados para adicionar novos itens à lista. Quando o botão é clicado, o texto do EditText é adicionado à lista, desde que o EditText não esteja vazio.*/
        val button = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.editText)

        button.setOnClickListener {
            if (editText.text.isEmpty()) {
                editText.error = "Preencha um valor"
                return@setOnClickListener
            }

            viewModel.addItem(editText.text.toString())

            editText.text.clear()
        }

        viewModel.itemsLiveData.observe(this) { items ->
            itemsAdapter.updateItems(items)
        }
    }
}
