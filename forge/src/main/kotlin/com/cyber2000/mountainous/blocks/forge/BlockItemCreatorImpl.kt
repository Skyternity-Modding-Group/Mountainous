import com.cyber2000.mountainous.blocks.BaseBlockItem
import com.cyber2000.mountainous.blocks.forge.BlockItem
import net.minecraft.item.Item
import net.minecraft.block.Block

object BlockItemCreatorImpl {
    @JvmStatic fun createBlockItem(blockLambda: () -> Block, settings: Item.Settings): BaseBlockItem {
        return BlockItem(blockLambda)
    }
}