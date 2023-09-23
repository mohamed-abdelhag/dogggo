/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.model.Dog
//import javax.sql.DataSource
import com.example.dogglers.data.DataSource


/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
) : RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    // Define a variable to hold the list of dog data
    private val dogs: List<Dog> = DataSource.dogs

    // Initialize view elements in the DogCardViewHolder
    class DogCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dogImage: ImageView = view.findViewById(R.id.dog_image)
        val dogName: TextView = view.findViewById(R.id.dog_name)
        val dogAge: TextView = view.findViewById(R.id.dog_age)
        val dogHobbies: TextView = view.findViewById(R.id.dog_hobbies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        // Inflate the appropriate layout based on the layout type
        val layoutId = when (layout) {
            Layout.GRID -> R.layout.grid_list_item
            else -> R.layout.vertical_horizontal_list_item
        }
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

        return DogCardViewHolder(view)
    }

    override fun getItemCount(): Int = dogs.size

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        // Get the data at the current position
        val dog = dogs[position]

        // Set the image resource for the current dog
        holder.dogImage.setImageResource(dog.imageResourceId)

        // Set the text for the current dog's name
        holder.dogName.text = dog.name

        // Set the text for the current dog's age
        holder.dogAge.text = context?.resources?.getString(R.string.dog_age, dog.age)

        // Set the text for the current dog's hobbies
        holder.dogHobbies.text = context?.resources?.getString(R.string.dog_hobbies, dog.hobbies)
    }

    // Constants for layout types
    object Layout {
        const val VERTICAL = 1
        const val HORIZONTAL = 2
        const val GRID = 3
    }
}