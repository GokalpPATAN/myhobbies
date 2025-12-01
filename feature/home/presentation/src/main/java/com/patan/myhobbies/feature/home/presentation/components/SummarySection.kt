import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.patan.myhobbies.feature.home.domain.model.UserSummary

@Composable
internal fun SummarySection(summary: UserSummary?) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = summary?.displayName ?: "Hoş geldin 👋",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            if (summary != null) {
                Text(
                    text = "Seviye: ${summary.level}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Toplam XP: ${summary.totalXp}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Tamamlanan hobiler: ${summary.completedHobbies}",
                    style = MaterialTheme.typography.bodyMedium
                )
            } else {
                Text(
                    text = "XP ve seviyen burada görünecek.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}