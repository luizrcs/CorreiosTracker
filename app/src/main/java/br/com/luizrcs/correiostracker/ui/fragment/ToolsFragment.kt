@file:OptIn(
	ExperimentalMaterialApi::class
)

package br.com.luizrcs.correiostracker.ui.fragment

import android.os.*
import android.view.*
import androidx.annotation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.platform.*
import androidx.compose.ui.platform.ViewCompositionStrategy.*
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import br.com.luizrcs.correiostracker.R
import kotlin.OptIn

class ToolsFragment: AppScreenFragment() {
	
	private val padding = 16.dp
	
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		return ComposeView(requireContext()).apply {
			setViewCompositionStrategy(DisposeOnLifecycleDestroyed(viewLifecycleOwner))
			
			setContent {
				MaterialTheme {
					Column {
						// Space by Toolbar height
						Spacer(modifier = Modifier.size(58.dp))
						
						Column(
							modifier = Modifier.padding(padding),
							verticalArrangement = Arrangement.spacedBy(padding)
						) {
							PreviewToolCard()
							PreviewToolCard()
							PreviewToolCard()
						}
					}
				}
			}
		}
	}
	
	@Composable
	fun ToolCard(
		title: String,
		description: String,
		@DrawableRes icon: Int,
		onClick: () -> Unit = {},
	) = Card(
		onClick = onClick,
		shape = RoundedCornerShape(16.dp),
		elevation = 4.dp
	) {
		Row(
			modifier = Modifier
				.padding(padding)
				.fillMaxWidth(),
			verticalAlignment = CenterVertically
		) {
			CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
				Icon(
					painter = painterResource(id = icon),
					contentDescription = "",
					modifier = Modifier.size(48.dp)
				)
			}
			Spacer(modifier = Modifier.size(padding))
			Column {
				Text(
					title,
					style = MaterialTheme.typography.h6
				)
				CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
					Text(
						description,
						style = MaterialTheme.typography.subtitle1
					)
				}
			}
		}
	}
	
	@Preview
	@Composable
	fun PreviewToolCard() = ToolCard(
		"Title",
		"Description",
		R.drawable.outline_home_24
	)
}