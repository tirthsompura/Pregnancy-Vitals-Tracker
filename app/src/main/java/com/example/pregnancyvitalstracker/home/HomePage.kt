import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pregnancyvitalstracker.DateTimeUtils.toDateTimeString
import com.example.pregnancyvitalstracker.components.AddVitalsDialog
import com.example.pregnancyvitalstracker.components.CommonTimerTile
import com.example.pregnancyvitalstracker.components.CommonTopBar
import com.example.pregnancyvitalstracker.components.PregnancyVitalsTile
import com.example.pregnancyvitalstracker.home.HomeViewModel
import com.example.pregnancyvitalstracker.ui.theme.black
import com.example.pregnancyvitalstracker.ui.theme.fontSemiBold
import com.example.pregnancyvitalstracker.ui.theme.purple
import com.example.pregnancyvitalstracker.ui.theme.white
import com.example.submissiondemo.dataStore.VitalsEntity
import kotlinx.coroutines.launch

@Composable
fun HomePage(
    isRunning: Boolean,
    currentTime: String,
    viewModel: HomeViewModel = hiltViewModel(),
    onTimerStart: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.loadVitals()
    }
    val vitalsList by viewModel.vitalsList.collectAsState()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    if (viewModel.isShowDialog.value) {
        AddVitalsDialog(
            firstText = viewModel.firstText,
            secondText = viewModel.secondText,
            weightValue = viewModel.weightValue,
            babyKickValue = viewModel.babyKickValue,
            showDialog = viewModel.isShowDialog,
            onSubmit = { firstText, secondText, weightValue, babyKickValue ->
                if (firstText.isNotBlank() && secondText.isNotBlank() && weightValue.isNotBlank() && babyKickValue.isNotBlank()) {
                    val newVitals = VitalsEntity(
                        sysBP = firstText,
                        diaBP = secondText,
                        weight = weightValue,
                        kicks = babyKickValue
                    )
                    viewModel.addVitals(newVitals) {
                        coroutineScope.launch {
                            Toast.makeText(
                                context, "Vitals added successfully!", Toast.LENGTH_SHORT
                            ).show()
                            viewModel.firstText.value = ""
                            viewModel.secondText.value = ""
                            viewModel.weightValue.value = ""
                            viewModel.babyKickValue.value = ""
                        }
                    }
                }
            })
    }


    Scaffold(containerColor = white, topBar = {
        CommonTopBar(
            modifier = Modifier, title = "Track My Pregnancy"
        )
    }, content = {
        HomeBody(
            modifier = Modifier.padding(it),
            vitalsList = vitalsList,
            currentTime = currentTime,
            onTimerStart = onTimerStart,
            isRunning = isRunning
        )
    }, floatingActionButton = {
        FloatingActionButton(
            modifier = Modifier, shape = CircleShape, content = {
                Icon(
                    Icons.Default.Add, contentDescription = "", modifier = Modifier.size(40.dp)
                )
            }, onClick = {
                viewModel.isShowDialog.value = true
            }, containerColor = purple, contentColor = white
        )
    })
}

@Composable
fun HomeBody(
    modifier: Modifier,
    vitalsList: List<VitalsEntity>,
    currentTime: String,
    onTimerStart: () -> Unit,
    isRunning: Boolean
) {
    Column(modifier = modifier.fillMaxSize()) {
        CommonTimerTile(
            isTimerRunning = isRunning,
            currentTime = currentTime,
            onStart = {
                onTimerStart()
            }
        )
        if (vitalsList.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 11.dp, vertical = 16.dp)
                    .fillMaxWidth()
            ) {
                items(vitalsList.size) { index ->
                    val vitalData = vitalsList[index]
                    PregnancyVitalsTile(
                        bpmValue = vitalData.sysBP,
                        kgValue = vitalData.weight,
                        mmHgValue = "${vitalData.sysBP}/${vitalData.diaBP}",
                        kicksValue = vitalData.kicks,
                        time = vitalData.timestamp.toDateTimeString()
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No vital data found.", style = fontSemiBold.copy(
                        fontSize = 16.sp,
                        color = black
                    )
                )
            }
        }
    }
}