import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thekittyapp.R
import com.example.thekittyapp.api.BreedResponse

class DataAdapter(private var dataList: List<BreedResponse>):
    RecyclerView.Adapter < DataAdapter.ViewHolder > () {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.listcard, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position], position)

    }

    class ViewHolder(val itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        fun bind(breedResponse: BreedResponse, position: Int) {
            val title = itemLayoutView.findViewById<TextView>(R.id.breed)
            val imageView = itemLayoutView.findViewById<ImageView>(R.id.imageView)
            val description = itemLayoutView.findViewById<TextView>(R.id.response_TextView)

            title.text = breedResponse.name
            description.text = breedResponse.description

//            title.text = breedResponse.breeds.first().name
//            description.text = breedResponse.breeds.first().description
            Glide.with(itemLayoutView.context)
                .load(breedResponse.wikipedia_url)
                .fitCenter()
                .into(imageView)
        }

    }

    fun filterList(filteredNames: ArrayList<BreedResponse>) {
        Log.e("list", filteredNames.toString())
        Log.e("list", filteredNames.size.toString())
        // this.dataList.clear()
        this.dataList = filteredNames
        notifyDataSetChanged()
    }
}

