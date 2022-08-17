package com.example.test4.community

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.viewpager2.widget.ViewPager2
import com.example.test4.R
import com.example.test4.adapter.ViewPagerAdapter_community
import com.example.test4.board.BoardInsideActivity
import com.example.test4.board.BoardListLVAdapter
import com.example.test4.board.BoardModel
import com.example.test4.board.BoardWriteActivity
import com.example.test4.databinding.FragmentCommunityBinding
import com.example.test4.utils.FBRef
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class CommunityFragment : Fragment() {

    private lateinit var binding : FragmentCommunityBinding

    private val boardDataList = mutableListOf<BoardModel>()
    private val boardKeyList = mutableListOf<String>()

    private val TAG = CommunityFragment::class.java.simpleName

    private lateinit var boardRVAdapter : BoardListLVAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_community, container, false)

        var writeBtn = view.findViewById<ImageView>(R.id.writeBtn)
        var boardListView = view.findViewById<ListView>(R.id.boardListView)


        boardRVAdapter = BoardListLVAdapter(boardDataList)
        boardListView.adapter = boardRVAdapter

        writeBtn.setOnClickListener {
            val intent = Intent(context, BoardWriteActivity::class.java)
            startActivity(intent)
        }

        // 텝 레이아웃
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        val viewPager2= view.findViewById<ViewPager2>(R.id.view_pager_2)

        val adapter= ViewPagerAdapter_community(activity?.supportFragmentManager!!,lifecycle)

        viewPager2.adapter=adapter

        //탭 레이아웃 이름 설정
        TabLayoutMediator(tabLayout,viewPager2){tab,position->
            when(position){
                0->{
                    tab.text="전체 커뮤니티"
                }
                1->{
                    tab.text="나의 커뮤니티"
                }
            }
        }.attach()

        return view

        //
        binding.boardListView.setOnItemClickListener { parent, view, position, id ->

            // 첫번째 방법으로는 listview에 있는 데이터 title content time 다 다른 액티비티로 전달해줘서 만들기
//            val intent = Intent(context, BoardInsideActivity::class.java)
//            intent.putExtra("title", boardDataList[position].title)
//            intent.putExtra("content", boardDataList[position].content)
//            intent.putExtra("time", boardDataList[position].time)
//            startActivity(intent)

            // 두번째 방법으로는 Firebase에 있는 board에 대한 데이터의 id를 기반으로 다시 데이터를 받아오는 방법
            val intent = Intent(context, BoardInsideActivity::class.java)
            intent.putExtra("key", boardKeyList[position])
            startActivity(intent)

        }

        getFBBoardData()

        return binding.root
    }

    private fun getFBBoardData(){

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                boardDataList.clear()

                for (dataModel in dataSnapshot.children) {

                    Log.d(TAG, dataModel.toString())
//                    dataModel.key

                    val item = dataModel.getValue(BoardModel::class.java)
                    boardDataList.add(item!!)
                    boardKeyList.add(dataModel.key.toString())

                }
                boardKeyList.reverse()
                boardDataList.reverse()
                boardRVAdapter.notifyDataSetChanged()

                Log.d(TAG, boardDataList.toString())


            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.boardRef.addValueEventListener(postListener)
    }
}