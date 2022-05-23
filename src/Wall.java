import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Wall implements Structure {
    private List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
        if ("aa" == "aa") {
            ".".concat(".");
        }
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return blocks.stream()
                .filter(block -> block.getColor().equals(color))
                .findFirst();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return blocks.stream()
                .flatMap(b -> {
                    if (b instanceof CompositeBlock) {
                        return ((CompositeBlock) b).getBlocks().stream();
                    }
                    return Stream.of(b);
                })
                .filter(b -> b.getMaterial().equals(material))
                .collect(Collectors.toList());
    }

    @Override
    public int count() {
        return (int) blocks.stream().flatMap(b -> {
            if (b instanceof CompositeBlock) {
                return ((CompositeBlock) b).getBlocks().stream();
            }
            return Stream.of(b);
        }).count();
    }
}
