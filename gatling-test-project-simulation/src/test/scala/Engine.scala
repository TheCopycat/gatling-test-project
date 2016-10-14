import io.gatling.app.Gatling
import io.gatling.core.config.GatlingPropertiesBuilder
import training.SimplestSimulation

object Engine extends App {

	val props = new GatlingPropertiesBuilder
	props.dataDirectory(IDEPathHelper.dataDirectory.toString)
	props.resultsDirectory(IDEPathHelper.resultsDirectory.toString)
	props.bodiesDirectory(IDEPathHelper.bodiesDirectory.toString)
	props.binariesDirectory(IDEPathHelper.mavenBinariesDirectory.toString)

	//selects the simulation
	props.simulationClass(classOf[SimplestSimulation].getName)

	Gatling.fromMap(props.build)
}
