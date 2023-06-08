package com.example.cookbook.ui.play_movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.cookbook.databinding.FragmentPlayMovieBinding


const val PLAY_MOVIE = "play_movie"

class PlayMovieFragment : Fragment() {
    private var _binding: FragmentPlayMovieBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(bundle: Bundle): PlayMovieFragment {
            val fragment = PlayMovieFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlayMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = arguments?.getString(PLAY_MOVIE) ?: ""
        binding.progressCircular.visibility=View.VISIBLE
        val webView = binding.webView
        if (url != "") {
            binding.emptyLink.visibility=View.GONE
            webView.visibility=View.VISIBLE
            val settings = webView.settings
            webView.loadUrl(url)
            settings.apply {
                javaScriptEnabled = true
                setSupportZoom(true)
                builtInZoomControls = true
            }
        }else{
            binding.emptyLink.visibility=View.VISIBLE
            webView.visibility=View.GONE
            binding?.progressCircular?.visibility=View.GONE
        }

        webView.webViewClient= object : WebViewClient()  {
            override fun onPageFinished(view: WebView?, url: String?) {
                try{
                    binding?.progressCircular?.visibility=View.GONE
                }catch (e:NullPointerException ){}
            }
        }
    }

}
